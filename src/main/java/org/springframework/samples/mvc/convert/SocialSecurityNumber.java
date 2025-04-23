package org.springframework.samples.mvc.convert;

/**
 * Value object for a social security number.
 * 
 * This class was auto-generated during Spring Boot migration.
 */
public final class SocialSecurityNumber {

    private final String value;

    public SocialSecurityNumber(String value) {
        this.value = value;
    }

    @MaskFormat("###-##-####")
    public String getValue() {
        return value;
    }

    public static SocialSecurityNumber valueOf(@MaskFormat("###-##-####") String value) {
        return new SocialSecurityNumber(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SocialSecurityNumber other = (SocialSecurityNumber) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}