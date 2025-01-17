/*
 * Copyright 2019-2021 the Tabuyos.
 */
package betalpha.impl;

import betalpha.TradingDayService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.betalpha.impl
 *   <b>class: </b>TradingDayServiceImpl
 *   comment here.
 * </pre>
 *
 * @author <pre><b>username: </b><a href="http://www.tabuyos.com">Tabuyos</a></pre>
 * <pre><b>site: </b><a href="http://www.tabuyos.com">http://www.tabuyos.com</a></pre>
 * <pre><b>email: </b>tabuyos@outlook.com</pre>
 * <pre><b>description: </b>
 *       <pre>
 *         Talk is cheap, show me the code.
 *       </pre>
 *     </pre>
 * @version 0.1.0
 * @since 0.1.0 - 2021/4/21 16:50
 */
public class TradingDayServiceImpl implements TradingDayService {

  private final List<String> list = Arrays.asList("2020-01-02", "2020-01-03", "2020-01-06", "2020-01-07", "2020-01-08",
                                                  "2020-01-09", "2020-01-10", "2020-01-13", "2020-01-14", "2020-01-15",
                                                  "2020-01-16", "2020-01-17", "2020-01-20", "2020-01-21", "2020-01-22",
                                                  "2020-01-23", "2020-02-03", "2020-02-04", "2020-02-05", "2020-02-06",
                                                  "2020-02-07", "2020-02-10", "2020-02-11", "2020-02-12", "2020-02-13",
                                                  "2020-02-14", "2020-02-17", "2020-02-18", "2020-02-19", "2020-02-20",
                                                  "2020-02-21", "2020-02-24", "2020-02-25", "2020-02-26", "2020-02-27",
                                                  "2020-02-28", "2020-03-02", "2020-03-03", "2020-03-04", "2020-03-05",
                                                  "2020-03-06", "2020-03-09", "2020-03-10", "2020-03-11", "2020-03-12",
                                                  "2020-03-13", "2020-03-16", "2020-03-17", "2020-03-18", "2020-03-19",
                                                  "2020-03-20", "2020-03-23", "2020-03-24", "2020-03-25", "2020-03-26",
                                                  "2020-03-27", "2020-03-30", "2020-03-31", "2020-04-01", "2020-04-02",
                                                  "2020-04-03", "2020-04-07", "2020-04-08", "2020-04-09", "2020-04-10",
                                                  "2020-04-13", "2020-04-14", "2020-04-15", "2020-04-16", "2020-04-17",
                                                  "2020-04-20", "2020-04-21", "2020-04-22", "2020-04-23", "2020-04-24",
                                                  "2020-04-27", "2020-04-28", "2020-04-29", "2020-04-30", "2020-05-06",
                                                  "2020-05-07", "2020-05-08", "2020-05-11", "2020-05-12", "2020-05-13",
                                                  "2020-05-14", "2020-05-15", "2020-05-18", "2020-05-19", "2020-05-20",
                                                  "2020-05-21", "2020-05-22", "2020-05-25", "2020-05-26", "2020-05-27",
                                                  "2020-05-28", "2020-05-29", "2020-06-01", "2020-06-02", "2020-06-03",
                                                  "2020-06-04", "2020-06-05", "2020-06-08", "2020-06-09", "2020-06-10",
                                                  "2020-06-11", "2020-06-12", "2020-06-15", "2020-06-16", "2020-06-17",
                                                  "2020-06-18", "2020-06-19", "2020-06-22", "2020-06-23", "2020-06-24",
                                                  "2020-06-29", "2020-06-30", "2020-07-01", "2020-07-02", "2020-07-03",
                                                  "2020-07-06", "2020-07-07", "2020-07-08", "2020-07-09", "2020-07-10",
                                                  "2020-07-13", "2020-07-14", "2020-07-15", "2020-07-16", "2020-07-17",
                                                  "2020-07-20", "2020-07-21", "2020-07-22", "2020-07-23", "2020-07-24",
                                                  "2020-07-27", "2020-07-28", "2020-07-29", "2020-07-30", "2020-07-31",
                                                  "2020-08-03", "2020-08-04", "2020-08-05", "2020-08-06", "2020-08-07",
                                                  "2020-08-10", "2020-08-11", "2020-08-12", "2020-08-13", "2020-08-14",
                                                  "2020-08-17", "2020-08-18", "2020-08-19", "2020-08-20", "2020-08-21",
                                                  "2020-08-24", "2020-08-25", "2020-08-26", "2020-08-27", "2020-08-28",
                                                  "2020-08-31", "2020-09-01", "2020-09-02", "2020-09-03", "2020-09-04",
                                                  "2020-09-07", "2020-09-08", "2020-09-09", "2020-09-10", "2020-09-11",
                                                  "2020-09-14", "2020-09-15", "2020-09-16", "2020-09-17", "2020-09-18",
                                                  "2020-09-21", "2020-09-22", "2020-09-23", "2020-09-24", "2020-09-25",
                                                  "2020-09-28", "2020-09-29", "2020-09-30", "2020-10-09", "2020-10-12",
                                                  "2020-10-13", "2020-10-14", "2020-10-15", "2020-10-16", "2020-10-19",
                                                  "2020-10-20", "2020-10-21", "2020-10-22", "2020-10-23", "2020-10-26",
                                                  "2020-10-27", "2020-10-28", "2020-10-29", "2020-10-30", "2020-11-02",
                                                  "2020-11-03", "2020-11-04", "2020-11-05", "2020-11-06", "2020-11-09",
                                                  "2020-11-10", "2020-11-11", "2020-11-12", "2020-11-13", "2020-11-16",
                                                  "2020-11-17", "2020-11-18", "2020-11-19", "2020-11-20", "2020-11-23",
                                                  "2020-11-24", "2020-11-25", "2020-11-26", "2020-11-27", "2020-11-30",
                                                  "2020-12-01", "2020-12-02", "2020-12-03", "2020-12-04", "2020-12-07",
                                                  "2020-12-08", "2020-12-09", "2020-12-10", "2020-12-11", "2020-12-14",
                                                  "2020-12-15", "2020-12-16", "2020-12-17", "2020-12-18", "2020-12-21",
                                                  "2020-12-22", "2020-12-23", "2020-12-24", "2020-12-25", "2020-12-28",
                                                  "2020-12-29", "2020-12-30", "2020-12-31");

  private final NavigableSet<LocalDate> tradingDays = parseTradingDay();

  @Override
  public boolean isTradingDay(LocalDate date) {
    return tradingDays.contains(date);
  }

  @Override
  public Optional<LocalDate> queryNextTradingDay(LocalDate date) {
    return Optional.ofNullable(tradingDays.higher(date));
  }

  @Override
  public List<LocalDate> queryBetween(LocalDate from, LocalDate to) {
    return new ArrayList<>(tradingDays.subSet(from, true, to, true));
  }

  @Override
  public Optional<LocalDate> queryFirstTradingDayOfMonth(LocalDate date) {
    LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
    return Optional.ofNullable(tradingDays.ceiling(localDate));
  }

  /**
   * 解析字符串为LocalDate
   *
   * @return 交易日列表
   */
  private NavigableSet<LocalDate> parseTradingDay(){
    return list
        .stream()
        .map(LocalDate::parse)
        .collect(Collectors.toCollection(TreeSet::new));
  }
}