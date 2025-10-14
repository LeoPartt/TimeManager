#!/bin/bash
set -e

echo "Starting Flutter web server with automatic hot reload"
echo "Watching: /app/lib/ and /app/pubspec.yaml"
echo ""

# Create named pipe for Flutter input
PIPE="/tmp/flutter_input"
rm -f "$PIPE"
mkfifo "$PIPE"

# Start Flutter in background with hot reload capability
# Flutter 3.35+ supports hot reload on web (no experimental flag needed)
tail -f "$PIPE" | flutter run -d web-server \
    --web-hostname 0.0.0.0 \
    --web-port 8080 &
FLUTTER_PID=$!

# Cleanup handler
cleanup() {
    echo ""
    echo "Stopping Flutter web server..."
    kill $FLUTTER_PID 2>/dev/null || true
    rm -f "$PIPE"
    exit
}
trap cleanup SIGTERM SIGINT EXIT

# Wait for Flutter to be ready
echo "⏳ Initializing Flutter..."
sleep 8

echo "✅ File watcher active - changes will trigger hot reload automatically"
echo ""

# Watch for file changes and trigger hot reload
while kill -0 $FLUTTER_PID 2>/dev/null; do
    # Watch lib/ directory and pubspec.yaml
    if inotifywait -q -r -e modify,create,delete,move \
        --exclude '\.tmp\.|\.dart_tool' \
        --timeout 300 \
        /app/lib /app/pubspec.yaml 2>/dev/null; then

        # Trigger hot reload
        echo "r" >> "$PIPE"
        echo "🔥 [$(date '+%H:%M:%S')] Hot reload triggered"
    fi
done

wait $FLUTTER_PID