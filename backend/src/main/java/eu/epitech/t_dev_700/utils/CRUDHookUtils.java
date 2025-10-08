package eu.epitech.t_dev_700.utils;

import eu.epitech.t_dev_700.services.CRUDService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CRUDHookUtils {

    public static <E> void beforeCreate(CRUDService<E, ?, ?, ?> service, E entity, Object request) {
        invokeHooks(service, Moment.BEFORE, Action.CREATE, entity, request);
    }

    public static <E> void afterCreate(CRUDService<E, ?, ?, ?> service, E entity, Object request) {
        invokeHooks(service, Moment.AFTER, Action.CREATE, entity, request);
    }

    public static <E> void beforeUpdate(CRUDService<E, ?, ?, ?> service, E entity, Object request) {
        invokeHooks(service, Moment.BEFORE, Action.UPDATE, entity, request);
    }

    public static <E> void afterUpdate(CRUDService<E, ?, ?, ?> service, E entity, Object request) {
        invokeHooks(service, Moment.AFTER, Action.UPDATE, entity, request);
    }

    public static <E> void beforeDelete(CRUDService<E, ?, ?, ?> service, E entity) {
        invokeHooks(service, Moment.BEFORE, entity);
    }

    public static <E> void afterDelete(CRUDService<E, ?, ?, ?> service, E entity) {
        invokeHooks(service, Moment.AFTER, entity);
    }

    private static <E> void invokeHooks(CRUDService<E, ?, ?, ?> service, Moment moment, Action action, E entity, Object request) {
        HookInvoker<E> invoker = new HookInvoker<>(service, entity, request);
        HookAnnotationPredicate predicate = new HookAnnotationPredicate(moment, action);
        invokeHooks(service, invoker, predicate);
    }

    private static <E> void invokeHooks(CRUDService<E, ?, ?, ?> service, Moment moment, E entity) {
        HookInvoker<E> invoker = new HookInvoker<>(service, entity);
        HookAnnotationPredicate predicate = new HookAnnotationPredicate(moment, Action.DELETE);
        invokeHooks(service, invoker, predicate);
    }

    private static <E> void invokeHooks(CRUDService<E, ?, ?, ?> service, HookInvoker<E> invoker, HookAnnotationPredicate predicate) {
        try {
            Arrays.stream(service.getClass().getMethods())
                    .filter(predicate)
                    .forEach(invoker);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private record HookAnnotationPredicate(Moment moment, Action action) implements Predicate<Method> {
        @Override
        public boolean test(Method method) {
            return method.isAnnotationPresent(CRUDHook.class)
                   && (method.getAnnotation(CRUDHook.class)).moment() == moment
                   && (method.getAnnotation(CRUDHook.class)).action() == action;
        }
    }

    private record HookInvoker<E>(CRUDService<E, ?, ?, ?> service, E entity,
                                  Object request) implements Consumer<Method> {
        public HookInvoker(CRUDService<E, ?, ?, ?> service, E entity) {
            this(service, entity, null);
        }

        public void accept(Method method) {
            try {
                if ((request == null)) method.invoke(entity);
                else method.invoke(entity, request);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public enum Moment {
        BEFORE,
        AFTER
    }

    public enum Action {
        CREATE,
        UPDATE,
        DELETE
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface CRUDHook {
        Moment moment();

        Action action();
    }

}
