package com.ruoyi.common.utils.bean;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolationException;

/**
 * bean对象属性验证
 * 
 * @author ruoyi
 */
public class BeanValidators
{
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 验证对象
     * 
     * @param object 待验证对象
     * @param groups 待验证的组
     * @throws ConstraintViolationException
     */
    @SuppressWarnings("rawtypes")
    public static void validateWithException(Object object, Class... groups) throws ConstraintViolationException
    {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty())
        {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
