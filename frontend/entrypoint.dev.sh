#!/bin/sh
set -e

echo "=== Flutter Development Mode with Hot Reload ==="

# Install inotify-tools if not present
if ! command -v inotifywait >/dev/null 2>&1; then
    echo "Installing inotify-tools..."
    apt-get update -qq && apt-get install -y -qq inotify-tools >/dev/null 2>&1
fi

# Create a named pipe for communication
PIPE=/tmp/flutter_input
mkfifo $PIPE || true

# Start Flutter with the pipe as input
echo "Starting Flutter web server..."
flutter run -d web-server --web-hostname 0.0.0.0 --web-port 8080 < $PIPE &
FLUTTER_PID=$!

# Give Flutter time to initialize
sleep 15

echo "Hot reload watcher active - watching /app/lib for changes"

# Watch for file changes and send 'r' to trigger hot reload
while true; do
    inotifywait -r -e modify,create,delete,move \
        --exclude '.*\.(swp|tmp|log)$' \
        /app/lib 2>/dev/null || true

    echo "File change detected at $(date '+%H:%M:%S')"
    echo "Triggering hot reload..."

    # Send 'r' command to Flutter via the named pipe
    echo "r" > $PIPE

    # Debounce - wait a bit before watching again
    sleep 2
done
