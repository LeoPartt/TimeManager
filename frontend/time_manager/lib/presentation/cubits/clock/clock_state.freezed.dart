// GENERATED CODE - DO NOT MODIFY BY HAND
// coverage:ignore-file
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'clock_state.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

// dart format off
T _$identity<T>(T value) => value;
/// @nodoc
mixin _$ClockState {





@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockState);
}


@override
int get hashCode => runtimeType.hashCode;

@override
String toString() {
  return 'ClockState()';
}


}

/// @nodoc
class $ClockStateCopyWith<$Res>  {
$ClockStateCopyWith(ClockState _, $Res Function(ClockState) __);
}


/// Adds pattern-matching-related methods to [ClockState].
extension ClockStatePatterns on ClockState {
/// A variant of `map` that fallback to returning `orElse`.
///
/// It is equivalent to doing:
/// ```dart
/// switch (sealedClass) {
///   case final Subclass value:
///     return ...;
///   case _:
///     return orElse();
/// }
/// ```

@optionalTypeArgs TResult maybeMap<TResult extends Object?>({TResult Function( _Initial value)?  initial,TResult Function( ClockLoading value)?  loading,TResult Function( ClockedIn value)?  clockedIn,TResult Function( ClockedOut value)?  clockedOut,TResult Function( ClockedError value)?  error,required TResult orElse(),}){
final _that = this;
switch (_that) {
case _Initial() when initial != null:
return initial(_that);case ClockLoading() when loading != null:
return loading(_that);case ClockedIn() when clockedIn != null:
return clockedIn(_that);case ClockedOut() when clockedOut != null:
return clockedOut(_that);case ClockedError() when error != null:
return error(_that);case _:
  return orElse();

}
}
/// A `switch`-like method, using callbacks.
///
/// Callbacks receives the raw object, upcasted.
/// It is equivalent to doing:
/// ```dart
/// switch (sealedClass) {
///   case final Subclass value:
///     return ...;
///   case final Subclass2 value:
///     return ...;
/// }
/// ```

@optionalTypeArgs TResult map<TResult extends Object?>({required TResult Function( _Initial value)  initial,required TResult Function( ClockLoading value)  loading,required TResult Function( ClockedIn value)  clockedIn,required TResult Function( ClockedOut value)  clockedOut,required TResult Function( ClockedError value)  error,}){
final _that = this;
switch (_that) {
case _Initial():
return initial(_that);case ClockLoading():
return loading(_that);case ClockedIn():
return clockedIn(_that);case ClockedOut():
return clockedOut(_that);case ClockedError():
return error(_that);case _:
  throw StateError('Unexpected subclass');

}
}
/// A variant of `map` that fallback to returning `null`.
///
/// It is equivalent to doing:
/// ```dart
/// switch (sealedClass) {
///   case final Subclass value:
///     return ...;
///   case _:
///     return null;
/// }
/// ```

@optionalTypeArgs TResult? mapOrNull<TResult extends Object?>({TResult? Function( _Initial value)?  initial,TResult? Function( ClockLoading value)?  loading,TResult? Function( ClockedIn value)?  clockedIn,TResult? Function( ClockedOut value)?  clockedOut,TResult? Function( ClockedError value)?  error,}){
final _that = this;
switch (_that) {
case _Initial() when initial != null:
return initial(_that);case ClockLoading() when loading != null:
return loading(_that);case ClockedIn() when clockedIn != null:
return clockedIn(_that);case ClockedOut() when clockedOut != null:
return clockedOut(_that);case ClockedError() when error != null:
return error(_that);case _:
  return null;

}
}
/// A variant of `when` that fallback to an `orElse` callback.
///
/// It is equivalent to doing:
/// ```dart
/// switch (sealedClass) {
///   case Subclass(:final field):
///     return ...;
///   case _:
///     return orElse();
/// }
/// ```

@optionalTypeArgs TResult maybeWhen<TResult extends Object?>({TResult Function()?  initial,TResult Function()?  loading,TResult Function( Clock clock)?  clockedIn,TResult Function( Clock clock)?  clockedOut,TResult Function( String message)?  error,required TResult orElse(),}) {final _that = this;
switch (_that) {
case _Initial() when initial != null:
return initial();case ClockLoading() when loading != null:
return loading();case ClockedIn() when clockedIn != null:
return clockedIn(_that.clock);case ClockedOut() when clockedOut != null:
return clockedOut(_that.clock);case ClockedError() when error != null:
return error(_that.message);case _:
  return orElse();

}
}
/// A `switch`-like method, using callbacks.
///
/// As opposed to `map`, this offers destructuring.
/// It is equivalent to doing:
/// ```dart
/// switch (sealedClass) {
///   case Subclass(:final field):
///     return ...;
///   case Subclass2(:final field2):
///     return ...;
/// }
/// ```

@optionalTypeArgs TResult when<TResult extends Object?>({required TResult Function()  initial,required TResult Function()  loading,required TResult Function( Clock clock)  clockedIn,required TResult Function( Clock clock)  clockedOut,required TResult Function( String message)  error,}) {final _that = this;
switch (_that) {
case _Initial():
return initial();case ClockLoading():
return loading();case ClockedIn():
return clockedIn(_that.clock);case ClockedOut():
return clockedOut(_that.clock);case ClockedError():
return error(_that.message);case _:
  throw StateError('Unexpected subclass');

}
}
/// A variant of `when` that fallback to returning `null`
///
/// It is equivalent to doing:
/// ```dart
/// switch (sealedClass) {
///   case Subclass(:final field):
///     return ...;
///   case _:
///     return null;
/// }
/// ```

@optionalTypeArgs TResult? whenOrNull<TResult extends Object?>({TResult? Function()?  initial,TResult? Function()?  loading,TResult? Function( Clock clock)?  clockedIn,TResult? Function( Clock clock)?  clockedOut,TResult? Function( String message)?  error,}) {final _that = this;
switch (_that) {
case _Initial() when initial != null:
return initial();case ClockLoading() when loading != null:
return loading();case ClockedIn() when clockedIn != null:
return clockedIn(_that.clock);case ClockedOut() when clockedOut != null:
return clockedOut(_that.clock);case ClockedError() when error != null:
return error(_that.message);case _:
  return null;

}
}

}

/// @nodoc


class _Initial implements ClockState {
  const _Initial();
  






@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is _Initial);
}


@override
int get hashCode => runtimeType.hashCode;

@override
String toString() {
  return 'ClockState.initial()';
}


}




/// @nodoc


class ClockLoading implements ClockState {
  const ClockLoading();
  






@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockLoading);
}


@override
int get hashCode => runtimeType.hashCode;

@override
String toString() {
  return 'ClockState.loading()';
}


}




/// @nodoc


class ClockedIn implements ClockState {
  const ClockedIn(this.clock);
  

 final  Clock clock;

/// Create a copy of ClockState
/// with the given fields replaced by the non-null parameter values.
@JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
$ClockedInCopyWith<ClockedIn> get copyWith => _$ClockedInCopyWithImpl<ClockedIn>(this, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockedIn&&(identical(other.clock, clock) || other.clock == clock));
}


@override
int get hashCode => Object.hash(runtimeType,clock);

@override
String toString() {
  return 'ClockState.clockedIn(clock: $clock)';
}


}

/// @nodoc
abstract mixin class $ClockedInCopyWith<$Res> implements $ClockStateCopyWith<$Res> {
  factory $ClockedInCopyWith(ClockedIn value, $Res Function(ClockedIn) _then) = _$ClockedInCopyWithImpl;
@useResult
$Res call({
 Clock clock
});




}
/// @nodoc
class _$ClockedInCopyWithImpl<$Res>
    implements $ClockedInCopyWith<$Res> {
  _$ClockedInCopyWithImpl(this._self, this._then);

  final ClockedIn _self;
  final $Res Function(ClockedIn) _then;

/// Create a copy of ClockState
/// with the given fields replaced by the non-null parameter values.
@pragma('vm:prefer-inline') $Res call({Object? clock = null,}) {
  return _then(ClockedIn(
null == clock ? _self.clock : clock // ignore: cast_nullable_to_non_nullable
as Clock,
  ));
}


}

/// @nodoc


class ClockedOut implements ClockState {
  const ClockedOut(this.clock);
  

 final  Clock clock;

/// Create a copy of ClockState
/// with the given fields replaced by the non-null parameter values.
@JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
$ClockedOutCopyWith<ClockedOut> get copyWith => _$ClockedOutCopyWithImpl<ClockedOut>(this, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockedOut&&(identical(other.clock, clock) || other.clock == clock));
}


@override
int get hashCode => Object.hash(runtimeType,clock);

@override
String toString() {
  return 'ClockState.clockedOut(clock: $clock)';
}


}

/// @nodoc
abstract mixin class $ClockedOutCopyWith<$Res> implements $ClockStateCopyWith<$Res> {
  factory $ClockedOutCopyWith(ClockedOut value, $Res Function(ClockedOut) _then) = _$ClockedOutCopyWithImpl;
@useResult
$Res call({
 Clock clock
});




}
/// @nodoc
class _$ClockedOutCopyWithImpl<$Res>
    implements $ClockedOutCopyWith<$Res> {
  _$ClockedOutCopyWithImpl(this._self, this._then);

  final ClockedOut _self;
  final $Res Function(ClockedOut) _then;

/// Create a copy of ClockState
/// with the given fields replaced by the non-null parameter values.
@pragma('vm:prefer-inline') $Res call({Object? clock = null,}) {
  return _then(ClockedOut(
null == clock ? _self.clock : clock // ignore: cast_nullable_to_non_nullable
as Clock,
  ));
}


}

/// @nodoc


class ClockedError implements ClockState {
  const ClockedError(this.message);
  

 final  String message;

/// Create a copy of ClockState
/// with the given fields replaced by the non-null parameter values.
@JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
$ClockedErrorCopyWith<ClockedError> get copyWith => _$ClockedErrorCopyWithImpl<ClockedError>(this, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockedError&&(identical(other.message, message) || other.message == message));
}


@override
int get hashCode => Object.hash(runtimeType,message);

@override
String toString() {
  return 'ClockState.error(message: $message)';
}


}

/// @nodoc
abstract mixin class $ClockedErrorCopyWith<$Res> implements $ClockStateCopyWith<$Res> {
  factory $ClockedErrorCopyWith(ClockedError value, $Res Function(ClockedError) _then) = _$ClockedErrorCopyWithImpl;
@useResult
$Res call({
 String message
});




}
/// @nodoc
class _$ClockedErrorCopyWithImpl<$Res>
    implements $ClockedErrorCopyWith<$Res> {
  _$ClockedErrorCopyWithImpl(this._self, this._then);

  final ClockedError _self;
  final $Res Function(ClockedError) _then;

/// Create a copy of ClockState
/// with the given fields replaced by the non-null parameter values.
@pragma('vm:prefer-inline') $Res call({Object? message = null,}) {
  return _then(ClockedError(
null == message ? _self.message : message // ignore: cast_nullable_to_non_nullable
as String,
  ));
}


}

// dart format on
