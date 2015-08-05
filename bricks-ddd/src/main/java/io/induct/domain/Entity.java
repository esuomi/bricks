package io.induct.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Immutable base implementation for {@link Identifiable}. Uses default fallback value for detecting identification
 * state.
 *
 * @since 2015-03-29
 */
public abstract class Entity<I extends Serializable & Comparable<I>>
        implements Identifiable<I>, Serializable, Comparable<Entity<I>> {

    private final I identity;
    private final I unidentifiedIdentity;

    protected Entity(I identity, I unidentifiedIdentity) {
        this.identity = identity;
        this.unidentifiedIdentity = unidentifiedIdentity;
    }

    @Override
    public I identity() {
        return identity;
    }

    @Override
    public boolean isIdentified() {
        return !isUnidentified();
    }

    @Override
    public boolean isUnidentified() {
        return identity.equals(unidentifiedIdentity);
    }

    @Override
    public int compareTo(Entity<I> o) {
        return identity.compareTo(o.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, unidentifiedIdentity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        return Objects.equals(this.identity, other.identity)
                && Objects.equals(this.unidentifiedIdentity, other.unidentifiedIdentity);
    }
}
