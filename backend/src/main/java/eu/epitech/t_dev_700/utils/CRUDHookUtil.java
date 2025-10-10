package eu.epitech.t_dev_700.utils;

import eu.epitech.t_dev_700.services.CRUDService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record CRUDHookUtil<E>(
        CRUDService<E, ?, ?, ?, ?> service,
        Moment moment,
        Action action,
        E entity,
        Object request
) implements Runnable, Predicate<Method>, Consumer<Method> {

    public static <E> void beforeCreate(CRUDService<E, ?, ?, ?, ?> service, E entity, Object request) {
        new CRUDHookUtil<>(service, Moment.BEFORE, Action.CREATE, entity, request).run();
    }

    public static <E> void afterCreate(CRUDService<E, ?, ?, ?, ?> service, E entity, Object request) {
        new CRUDHookUtil<>(service, Moment.AFTER, Action.CREATE, entity, request).run();
    }

    public static <E> void beforeUpdate(CRUDService<E, ?, ?, ?, ?> service, E entity, Object request) {
        new CRUDHookUtil<>(service, Moment.BEFORE, Action.UPDATE, entity, request).run();
    }

    public static <E> void afterUpdate(CRUDService<E, ?, ?, ?, ?> service, E entity, Object request) {
        new CRUDHookUtil<>(service, Moment.AFTER, Action.UPDATE, entity, request).run();
    }

    public static <E> void beforeReplace(CRUDService<E, ?, ?, ?, ?> service, E entity, Object request) {
        new CRUDHookUtil<>(service, Moment.BEFORE, Action.REPLACE, entity, request).run();
    }

    public static <E> void afterReplace(CRUDService<E, ?, ?, ?, ?> service, E entity, Object request) {
        new CRUDHookUtil<>(service, Moment.AFTER, Action.REPLACE, entity, request).run();
    }

    public static <E> void beforeDelete(CRUDService<E, ?, ?, ?, ?> service, E entity) {
        new CRUDHookUtil<>(service, Moment.BEFORE, entity).run();
    }

    public static <E> void afterDelete(CRUDService<E, ?, ?, ?, ?> service, E entity) {
        new CRUDHookUtil<>(service, Moment.AFTER, entity).run();
    }

    public CRUDHookUtil(CRUDService<E, ?, ?, ?, ?> service, Moment moment, E entity) {
        this(service, moment, Action.DELETE, entity, null);
    }

    @Override
    public void run() {
        try {
            Arrays.stream(service.getClass().getMethods())
                    .filter(this)
                    .forEach(this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke CRUD hooks", e);
        }
    }

    @Override
    public boolean test(Method method) {
        return method.isAnnotationPresent(CRUDHook.class)
               && (method.getAnnotation(CRUDHook.class)).moment() == moment
               && (method.getAnnotation(CRUDHook.class)).action() == action;
    }

    public void accept(Method method) {
        try {
            method.setAccessible(true);
            Class<?>[] params = method.getParameterTypes();

            if (params.length == 1 && params[0].isInstance(entity)) {
                method.invoke(service, entity);
            } else if (params.length == 2 && params[0].isInstance(entity) && params[1].isInstance(request)) {
                method.invoke(service, entity, request);
            } else {
                throw new InvalidMethodSignature(method);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Moment {
        BEFORE,
        AFTER
    }

    public enum Action {
        CREATE,
        UPDATE,
        REPLACE,
        DELETE
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CRUDHook {
        Moment moment();

        Action action();
    }

    static class InvalidMethodSignature extends RuntimeException {
        public InvalidMethodSignature(Method method) {
            super("Invalid CRUDHookUtil method signature: " + method);
        }
    }

}
