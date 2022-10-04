package com.aws.demo.utils;

import com.aws.demo.data.dto.BookingDto;
import com.aws.demo.data.entity.ReservationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class DtoUtil {

    public static ReservationEntity convertToReserveEntity(BookingDto bookingDto) {
        ModelMapper mapper = new CustomModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(bookingDto, ReservationEntity.class);
    }

    public static BookingDto convertToReserveDto(ReservationEntity reservationEntity) {
        ModelMapper mapper = new CustomModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(reservationEntity, BookingDto.class);
    }
}
