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

 int get id; String? get username; String? get email; String? get avatarUrl; String? get phoneNumber; String? get firstName; String? get lastName;
/// Create a copy of UpdateUserProfileParams
/// with the given fields replaced by the non-null parameter values.
@JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
$UpdateUserProfileParamsCopyWith<UpdateUserProfileParams> get copyWith => _$UpdateUserProfileParamsCopyWithImpl<UpdateUserProfileParams>(this as UpdateUserProfileParams, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is UpdateUserProfileParams&&(identical(other.id, id) || other.id == id)&&(identical(other.username, username) || other.username == username)&&(identical(other.email, email) || other.email == email)&&(identical(other.avatarUrl, avatarUrl) || other.avatarUrl == avatarUrl)&&(identical(other.phoneNumber, phoneNumber) || other.phoneNumber == phoneNumber)&&(identical(other.firstName, firstName) || other.firstName == firstName)&&(identical(other.lastName, lastName) || other.lastName == lastName));
}


@override
int get hashCode => Object.hash(runtimeType,id,username,email,avatarUrl,phoneNumber,firstName,lastName);

@override
String toString() {
  return 'UpdateUserProfileParams(id: $id, username: $username, email: $email, avatarUrl: $avatarUrl, phoneNumber: $phoneNumber, firstName: $firstName, lastName: $lastName)';
}


}

/// @nodoc
abstract mixin class $UpdateUserProfileParamsCopyWith<$Res>  {
  factory $UpdateUserProfileParamsCopyWith(UpdateUserProfileParams value, $Res Function(UpdateUserProfileParams) _then) = _$UpdateUserProfileParamsCopyWithImpl;
@useResult
$Res call({
 int id, String? username, String? email, String? avatarUrl, String? phoneNumber, String? firstName, String? lastName
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
@pragma('vm:prefer-inline') @override $Res call({Object? id = null,Object? username = freezed,Object? email = freezed,Object? avatarUrl = freezed,Object? phoneNumber = freezed,Object? firstName = freezed,Object? lastName = freezed,}) {
  return _then(_self.copyWith(
id: null == id ? _self.id : id // ignore: cast_nullable_to_non_nullable
as int,username: freezed == username ? _self.username : username // ignore: cast_nullable_to_non_nullable
as String?,email: freezed == email ? _self.email : email // ignore: cast_nullable_to_non_nullable
as String?,avatarUrl: freezed == avatarUrl ? _self.avatarUrl : avatarUrl // ignore: cast_nullable_to_non_nullable
as String?,phoneNumber: freezed == phoneNumber ? _self.phoneNumber : phoneNumber // ignore: cast_nullable_to_non_nullable
as String?,firstName: freezed == firstName ? _self.firstName : firstName // ignore: cast_nullable_to_non_nullable
as String?,lastName: freezed == lastName ? _self.lastName : lastName // ignore: cast_nullable_to_non_nullable
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

@optionalTypeArgs TResult maybeWhen<TResult extends Object?>(TResult Function( int id,  String? username,  String? email,  String? avatarUrl,  String? phoneNumber,  String? firstName,  String? lastName)?  $default,{required TResult orElse(),}) {final _that = this;
switch (_that) {
case _UpdateUserProfileParams() when $default != null:
return $default(_that.id,_that.username,_that.email,_that.avatarUrl,_that.phoneNumber,_that.firstName,_that.lastName);case _:
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

@optionalTypeArgs TResult when<TResult extends Object?>(TResult Function( int id,  String? username,  String? email,  String? avatarUrl,  String? phoneNumber,  String? firstName,  String? lastName)  $default,) {final _that = this;
switch (_that) {
case _UpdateUserProfileParams():
return $default(_that.id,_that.username,_that.email,_that.avatarUrl,_that.phoneNumber,_that.firstName,_that.lastName);case _:
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

@optionalTypeArgs TResult? whenOrNull<TResult extends Object?>(TResult? Function( int id,  String? username,  String? email,  String? avatarUrl,  String? phoneNumber,  String? firstName,  String? lastName)?  $default,) {final _that = this;
switch (_that) {
case _UpdateUserProfileParams() when $default != null:
return $default(_that.id,_that.username,_that.email,_that.avatarUrl,_that.phoneNumber,_that.firstName,_that.lastName);case _:
  return null;

}
}

}

/// @nodoc


class _UpdateUserProfileParams implements UpdateUserProfileParams {
  const _UpdateUserProfileParams({required this.id, this.username, this.email, this.avatarUrl, this.phoneNumber, this.firstName, this.lastName});
  

@override final  int id;
@override final  String? username;
@override final  String? email;
@override final  String? avatarUrl;
@override final  String? phoneNumber;
@override final  String? firstName;
@override final  String? lastName;

/// Create a copy of UpdateUserProfileParams
/// with the given fields replaced by the non-null parameter values.
@override @JsonKey(includeFromJson: false, includeToJson: false)
@pragma('vm:prefer-inline')
_$UpdateUserProfileParamsCopyWith<_UpdateUserProfileParams> get copyWith => __$UpdateUserProfileParamsCopyWithImpl<_UpdateUserProfileParams>(this, _$identity);



@override
bool operator ==(Object other) {
  return identical(this, other) || (other.runtimeType == runtimeType&&other is _UpdateUserProfileParams&&(identical(other.id, id) || other.id == id)&&(identical(other.username, username) || other.username == username)&&(identical(other.email, email) || other.email == email)&&(identical(other.avatarUrl, avatarUrl) || other.avatarUrl == avatarUrl)&&(identical(other.phoneNumber, phoneNumber) || other.phoneNumber == phoneNumber)&&(identical(other.firstName, firstName) || other.firstName == firstName)&&(identical(other.lastName, lastName) || other.lastName == lastName));
}


@override
int get hashCode => Object.hash(runtimeType,id,username,email,avatarUrl,phoneNumber,firstName,lastName);

@override
String toString() {
  return 'UpdateUserProfileParams(id: $id, username: $username, email: $email, avatarUrl: $avatarUrl, phoneNumber: $phoneNumber, firstName: $firstName, lastName: $lastName)';
}


}

/// @nodoc
abstract mixin class _$UpdateUserProfileParamsCopyWith<$Res> implements $UpdateUserProfileParamsCopyWith<$Res> {
  factory _$UpdateUserProfileParamsCopyWith(_UpdateUserProfileParams value, $Res Function(_UpdateUserProfileParams) _then) = __$UpdateUserProfileParamsCopyWithImpl;
@override @useResult
$Res call({
 int id, String? username, String? email, String? avatarUrl, String? phoneNumber, String? firstName, String? lastName
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
@override @pragma('vm:prefer-inline') $Res call({Object? id = null,Object? username = freezed,Object? email = freezed,Object? avatarUrl = freezed,Object? phoneNumber = freezed,Object? firstName = freezed,Object? lastName = freezed,}) {
  return _then(_UpdateUserProfileParams(
id: null == id ? _self.id : id // ignore: cast_nullable_to_non_nullable
as int,username: freezed == username ? _self.username : username // ignore: cast_nullable_to_non_nullable
as String?,email: freezed == email ? _self.email : email // ignore: cast_nullable_to_non_nullable
as String?,avatarUrl: freezed == avatarUrl ? _self.avatarUrl : avatarUrl // ignore: cast_nullable_to_non_nullable
as String?,phoneNumber: freezed == phoneNumber ? _self.phoneNumber : phoneNumber // ignore: cast_nullable_to_non_nullable
as String?,firstName: freezed == firstName ? _self.firstName : firstName // ignore: cast_nullable_to_non_nullable
as String?,lastName: freezed == lastName ? _self.lastName : lastName // ignore: cast_nullable_to_non_nullable
as String?,
  ));
}


}

// dart format on
