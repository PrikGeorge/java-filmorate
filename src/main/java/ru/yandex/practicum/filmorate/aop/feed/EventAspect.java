package ru.yandex.practicum.filmorate.aop.feed;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.*;
import ru.yandex.practicum.filmorate.storage.event.EventStorage;

import java.util.Objects;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 22.12.2022
 */

@Aspect
@Component
public class EventAspect {

    private final EventStorage eventStorage;

    public EventAspect(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Pointcut("@annotation(AddEvent)")
    public void addMethod() {
    }

    @AfterReturning(pointcut = "addMethod()", returning = "returnObject")
    public void afterAddMethod(JoinPoint jp, Object returnObject) {
        if (Objects.nonNull(returnObject)) {
            if (returnObject instanceof User) {
                createEvent((long) jp.getArgs()[0], (long) jp.getArgs()[1], EventType.FRIEND, Operation.ADD);
            } else if (returnObject instanceof Film) {
                createEvent((long) jp.getArgs()[1], (long) jp.getArgs()[0], EventType.LIKE, Operation.ADD);
            } else if (returnObject instanceof Review) {
                createEvent(((Review) returnObject).getUserId(), ((Review) returnObject).getReviewId(), EventType.REVIEW, Operation.ADD);
            }
        }
    }

    @Pointcut("@annotation(RemoveEvent)")
    public void removeMethod() {
    }

    @AfterReturning(pointcut = "removeMethod()", returning = "returnObject")
    public void afterRemoveMethod(JoinPoint jp, Object returnObject) {
        if (Objects.nonNull(returnObject)) {
            if (returnObject instanceof User) {
                createEvent((long) jp.getArgs()[0], (long) jp.getArgs()[1], EventType.FRIEND, Operation.REMOVE);
            } else if (returnObject instanceof Film) {
                createEvent((long) jp.getArgs()[1], (long) jp.getArgs()[0], EventType.LIKE, Operation.REMOVE);
            } else if (returnObject instanceof Review) {
                createEvent(((Review) returnObject).getUserId(), ((Review) returnObject).getReviewId(), EventType.REVIEW, Operation.REMOVE);
            }
        }
    }

    @Pointcut("@annotation(UpdateEvent)")
    public void updateMethod() {
    }

    @AfterReturning(pointcut = "updateMethod()", returning = "returnObject")
    public void afterUpdateMethod(JoinPoint jp, Object returnObject) {
        if (Objects.nonNull(returnObject) && (returnObject instanceof Review)) {
            createEvent(((Review) returnObject).getUserId(), ((Review) returnObject).getReviewId(), EventType.REVIEW, Operation.UPDATE);
        }
    }

    private void createEvent(long userId, long entityId, EventType eventType, Operation operation) {
        eventStorage.create(Event.builder()
                .userId(userId)
                .entityId(entityId)
                .eventType(eventType)
                .operation(operation)
                .build());
    }
}
