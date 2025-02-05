package com.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author shanli
 */
@Getter
@Setter
public class AddDto {

    public Integer ab;

    private LocalDateTime start;

    private LocalDateTime end;
}
