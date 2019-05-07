package com.lancslot.morn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * @Description stream累加类
 * 用法：
 * StreamSumUtil<ArmyControlReportDTO> sumUtil = new StreamSumUtil<ArmyControlReportDTO>();
 * union.setGoalFlow(sumUtil.sumInt(streamSupplier, ArmyControlReportDTO::getGoalFlow, e -> e.getGoalFlow() != null));
 * @Date 2018/12/21 14:08
 **/
public class StreamSumUtil<T> {
    private static final Logger logger = LoggerFactory.getLogger(StreamSumUtil.class);

    public <T> Integer sumInt(Supplier<Stream<T>> streamSupplier,
                              ToIntFunction<? super T> mapper,
                              Predicate<T> predicate) {
        try {
            return streamSupplier.get()
                    .filter(predicate)
                    .mapToInt(mapper)
                    .sum();
        } catch (Exception e) {
            //ignore
            logger.error("sumInt error", e);
        }
        return 0;
    }

    public <T> BigDecimal sumBigDecimal(Supplier<Stream<T>> streamSupplier,
                                        Function<T, BigDecimal> mapper) {
        try {
            return streamSupplier.get()
                    .map(mapper)
                    .filter(e -> e != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (Exception e) {
            //ignore
            logger.error("sumBigDecimal error", e);
        }
        return BigDecimal.ZERO;
    }
}
