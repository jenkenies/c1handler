package com.utstar.c1handler.enums;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Properties;

public class EnumType implements UserType, DynamicParameterizedType {
    private Class enumClass;
    private static final int[] SQL_TYPES = new int[]{Types.CHAR};

    @Override
    public void setParameterValues(Properties properties) {
        ParameterType parameterType = (ParameterType) properties.get(PARAMETER_TYPE);
        if (parameterType != null) {
            enumClass = parameterType.getReturnedClass().asSubclass(Enum.class);
        }
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return enumClass;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == null && o1 == null) {
            return true;
        }
        if ((o != null && o1 == null) || (o == null && o1 != null)) {
            return false;
        }
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String value = resultSet.getString(strings[0]);
        if (value == null) {
            return null;
        } else {
            for (Object object : enumClass.getEnumConstants()) {
                if (Objects.equals(value, ((BaseEnum) object).getValue())) {
                    return object;
                }
            }
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setNull(i, SQL_TYPES[0]);
        } else if (o instanceof String) {
            preparedStatement.setString(i, (String) o);
        } else {
            preparedStatement.setString(i, ((BaseEnum) o).getValue());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}