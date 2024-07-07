package org.serenity.blazecom.handler;

/**
 * @apiNote Processing priority of Handler
 */
public enum Priority {

    LOWEST(-2),
    LOW(-1),
    MEDIUM(0),
    HIGH(1),
    HIGHEST(2),
    PRESERVED(3);
    public final int id;
    Priority(int i) {
        id = i;
    }

}
