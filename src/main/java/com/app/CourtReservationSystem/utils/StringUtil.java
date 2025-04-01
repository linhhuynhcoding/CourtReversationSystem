package com.app.CourtReservationSystem.utils;

import com.app.CourtReservationSystem.enums.CourtSortField;
import com.app.CourtReservationSystem.exception.APIException;
import com.cloudinary.api.exceptions.ApiException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public final class StringUtil {
    public static <T extends Enum<T>> List<Sort.Order> toOrders(String sort, Class<T> enumClass) throws ApiException {
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            sort = sort.substring(2, sort.length() - 2); // remove '[' and ']'
            System.out.println(sort);

            String[] sorts = sort.split(",]"); //convert to String

            for (String _sort : sorts) {
                System.out.println(_sort);
                Sort.Direction direction = Sort.Direction.valueOf(_sort.split(",")[1]);
                T field = Enum.valueOf(enumClass,  _sort.split(",")[0]);

                orders.add(new Sort.Order(direction, enumClass.getMethod("getValue").invoke(field).toString()));
            }

            return orders;
        } catch (IllegalArgumentException e) {
            throw new APIException("Sort query is invalid!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new APIException("Sort query is invalid!", HttpStatus.BAD_REQUEST);
        }
    }}
