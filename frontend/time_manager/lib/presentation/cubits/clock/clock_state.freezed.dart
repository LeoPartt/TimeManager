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

@optionalTypeArgs TResult maybeMap<TResult extends Object?>({TResult Function( ClockInState value)?  clockIn,TResult Function( ClockOutState value)?  clockOut,required TResult orElse(),}){
final _that = this;
switch (_that) {
case ClockInState() when clockIn != null:
return clockIn(_that);case ClockOutState() when clockOut != null:
return clockOut(_that);case _:
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

@optionalTypeArgs TResult map<TResult extends Object?>({required TResult Function( ClockInState value)  clockIn,required TResult Function( ClockOutState value)  clockOut,}){
final _that = this;
switch (_that) {
case ClockInState():
return clockIn(_that);case ClockOutState():
return clockOut(_that);case _:
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

@optionalTypeArgs TResult? mapOrNull<TResult extends Object?>({TResult? Function( ClockInState value)?  clockIn,TResult? Function( ClockOutState value)?  clockOut,}){
final _that = this;
switch (_that) {
case ClockInState() when clockIn != null:
return clockIn(_that);case ClockOutState() when clockOut != null:
return clockOut(_that);case _:
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

@optionalTypeArgs TResult maybeWhen<TResult extends Object?>({TResult Function()?  clockIn,TResult Function()?  clockOut,required TResult orElse(),}) {final _that = this;
switch (_that) {
case ClockInState() when clockIn != null:
return clockIn();case ClockOutState() when clockOut != null:
return clockOut();case _:
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

@optionalTypeArgs TResult when<TResult extends Object?>({required TResult Function()  clockIn,required TResult Function()  clockOut,}) {final _that = this;
switch (_that) {
case ClockInState():
return clockIn();case ClockOutState():
return clockOut();case _:
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

@optionalTypeArgs TResult? whenOrNull<TResult extends Object?>({TResult? Function()?  clockIn,TResult? Function()?  clockOut,}) {final _that = this;
switch (_that) {
case ClockInState() when clockIn != null:
return clockIn();case ClockOutState() when clockOut != null:
return clockOut();case _:
  return null;

}
}

}

/// @nodoc


class ClockInState implements ClockState {
  const ClockInState();
  






@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockInState);
}


@override
int get hashCode => runtimeType.hashCode;

@override
String toString() {
  return 'ClockState.clockIn()';
}


}




/// @nodoc


class ClockOutState implements ClockState {
  const ClockOutState();
  






@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is ClockOutState);
}


@override
int get hashCode => runtimeType.hashCode;

@override
String toString() {
  return 'ClockState.clockOut()';
}


}




// dart format on
