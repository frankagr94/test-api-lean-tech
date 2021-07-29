package com.leantech.test.context.domain;

import com.leantech.test.persistence.entity.PositionEntityMother;

/**
 * Position Mother
 */
public class PositionMother {

    public static Position valid(){
        return Position.load(PositionEntityMother.generate());
    }
}
