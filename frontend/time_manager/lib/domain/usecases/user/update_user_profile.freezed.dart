// GENERATED CODE - DO NOT MODIFY BY HAND
// coverage:ignore-file
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'update_user_profile.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

// dart format off
T _$identity<T>(T value) => value;
/// @nodoc
mixin _$UpdateUserProfileParams {

 String? get name; String? get email; String? get avatarUrl; String? get phone;
/// Create a copy of UpdateUserProfileParams
/// with the given fields replaced by the non-null parameter values.
@JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
$UpdateUserProfileParamsCopyWith<UpdateUserProfileParams> get copyWith => _$UpdateUserProfileParamsCopyWithImpl<UpdateUserProfileParams>(this as UpdateUserProfileParams, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is UpdateUserProfileParams&&(identical(other.name, name) || other.name == name)&&(identical(other.email, email) || other.email == email)&&(identical(other.avatarUrl, avatarUrl) || other.avatarUrl == avatarUrl)&&(identical(other.phone, phone) || other.phone == phone));
}


@override
int get hashCode => Object.hash(runtimeType,name,email,avatarUrl,phone);

@override
String toString() {
  return 'UpdateUserProfileParams(name: $name, email: $email, avatarUrl: $avatarUrl, phone: $phone)';
}


}

/// @nodoc
abstract mixin class $UpdateUserProfileParamsCopyWith<$Res>  {
  factory $UpdateUserProfileParamsCopyWith(UpdateUserProfileParams value, $Res Function(UpdateUserProfileParams) _then) = _$UpdateUserProfileParamsCopyWithImpl;
@useResult
$Res call({
 String? name, String? email, String? avatarUrl, String? phone
});




}
/// @nodoc
class _$UpdateUserProfileParamsCopyWithImpl<$Res>
    implements $UpdateUserProfileParamsCopyWith<$Res> {
  _$UpdateUserProfileParamsCopyWithImpl(this._self, this._then);

  final UpdateUserProfileParams _self;
  final $Res Function(UpdateUserProfileParams) _then;

/// Create a copy of UpdateUserProfileParams
/// with the given fields replaced by the non-null parameter values.
@pragma('vm:prefer-inline') @override $Res call({Object? name = freezed,Object? email = freezed,Object? avatarUrl = freezed,Object? phone = freezed,}) {
  return _then(_self.copyWith(
name: freezed == name ? _self.name : name // ignore: cast_nullable_to_non_nullable
as String?,email: freezed == email ? _self.email : email // ignore: cast_nullable_to_non_nullable
as String?,avatarUrl: freezed == avatarUrl ? _self.avatarUrl : avatarUrl // ignore: cast_nullable_to_non_nullable
as String?,phone: freezed == phone ? _self.phone : phone // ignore: cast_nullable_to_non_nullable
as String?,
  ));
}

}


/// Adds pattern-matching-related methods to [UpdateUserProfileParams].
extension UpdateUserProfileParamsPatterns on UpdateUserProfileParams {
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

@optionalTypeArgs TResult maybeMap<TResult extends Object?>(TResult Function( _UpdateUserProfileParams value)?  $default,{required TResult orElse(),}){
final _that = this;
switch (_that) {
case _UpdateUserProfileParams() when $default != null:
return $default(_that);case _:
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

@optionalTypeArgs TResult map<TResult extends Object?>(TResult Function( _UpdateUserProfileParams value)  $default,){
final _that = this;
switch (_that) {
case _UpdateUserProfileParams():
return $default(_that);case _:
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

@optionalTypeArgs TResult? mapOrNull<TResult extends Object?>(TResult? Function( _UpdateUserProfileParams value)?  $default,){
final _that = this;
switch (_that) {
case _UpdateUserProfileParams() when $default != null:
return $default(_that);case _:
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

@optionalTypeArgs TResult maybeWhen<TResult extends Object?>(TResult Function( String? name,  String? email,  String? avatarUrl,  String? phone)?  $default,{required TResult orElse(),}) {final _that = this;
switch (_that) {
case _UpdateUserProfileParams() when $default != null:
return $default(_that.name,_that.email,_that.avatarUrl,_that.phone);case _:
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

@optionalTypeArgs TResult when<TResult extends Object?>(TResult Function( String? name,  String? email,  String? avatarUrl,  String? phone)  $default,) {final _that = this;
switch (_that) {
case _UpdateUserProfileParams():
return $default(_that.name,_that.email,_that.avatarUrl,_that.phone);case _:
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

@optionalTypeArgs TResult? whenOrNull<TResult extends Object?>(TResult? Function( String? name,  String? email,  String? avatarUrl,  String? phone)?  $default,) {final _that = this;
switch (_that) {
case _UpdateUserProfileParams() when $default != null:
return $default(_that.name,_that.email,_that.avatarUrl,_that.phone);case _:
  return null;

}
}

}

/// @nodoc


class _UpdateUserProfileParams implements UpdateUserProfileParams {
  const _UpdateUserProfileParams({this.name, this.email, this.avatarUrl, this.phone});
  

@override final  String? name;
@override final  String? email;
@override final  String? avatarUrl;
@override final  String? phone;

/// Create a copy of UpdateUserProfileParams
/// with the given fields replaced by the non-null parameter values.
@override @JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
_$UpdateUserProfileParamsCopyWith<_UpdateUserProfileParams> get copyWith => __$UpdateUserProfileParamsCopyWithImpl<_UpdateUserProfileParams>(this, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is _UpdateUserProfileParams&&(identical(other.name, name) || other.name == name)&&(identical(other.email, email) || other.email == email)&&(identical(other.avatarUrl, avatarUrl) || other.avatarUrl == avatarUrl)&&(identical(other.phone, phone) || other.phone == phone));
}


@override
int get hashCode => Object.hash(runtimeType,name,email,avatarUrl,phone);

@override
String toString() {
  return 'UpdateUserProfileParams(name: $name, email: $email, avatarUrl: $avatarUrl, phone: $phone)';
}


}

/// @nodoc
abstract mixin class _$UpdateUserProfileParamsCopyWith<$Res> implements $UpdateUserProfileParamsCopyWith<$Res> {
  factory _$UpdateUserProfileParamsCopyWith(_UpdateUserProfileParams value, $Res Function(_UpdateUserProfileParams) _then) = __$UpdateUserProfileParamsCopyWithImpl;
@override @useResult
$Res call({
 String? name, String? email, String? avatarUrl, String? phone
});




}
/// @nodoc
class __$UpdateUserProfileParamsCopyWithImpl<$Res>
    implements _$UpdateUserProfileParamsCopyWith<$Res> {
  __$UpdateUserProfileParamsCopyWithImpl(this._self, this._then);

  final _UpdateUserProfileParams _self;
  final $Res Function(_UpdateUserProfileParams) _then;

/// Create a copy of UpdateUserProfileParams
/// with the given fields replaced by the non-null parameter values.
@override @pragma('vm:prefer-inline') $Res call({Object? name = freezed,Object? email = freezed,Object? avatarUrl = freezed,Object? phone = freezed,}) {
  return _then(_UpdateUserProfileParams(
name: freezed == name ? _self.name : name // ignore: cast_nullable_to_non_nullable
as String?,email: freezed == email ? _self.email : email // ignore: cast_nullable_to_non_nullable
as String?,avatarUrl: freezed == avatarUrl ? _self.avatarUrl : avatarUrl // ignore: cast_nullable_to_non_nullable
as String?,phone: freezed == phone ? _self.phone : phone // ignore: cast_nullable_to_non_nullable
as String?,
  ));
}


}

// dart format on
