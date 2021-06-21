package betalpha;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b>blu-ray
 *   <b>package: </b>com.tabuyos.betalpha
 *   <b>class: </b>TradingDayService
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
 * @version 1.0
 * @since 1.0 - 2021/4/21 16:42
 */
public interface TradingDayService {

  /**
   * 判断是否为交易日
   * @param date 交易日
   * @return 是否为交易日
   */
  boolean isTradingDay(LocalDate date);

  /**
   * 查询下一个交易日
   * @param date 当前交易日
   * @return 下一个交易日
   */
  Optional<LocalDate> queryNextTradingDay(LocalDate date);

  /**
   * 查询一段时间范围的交易日, 注意包括[from, to]
   * @param from 开始交易日
   * @param to 结束交易日
   * @return 交易日范围
   */
  List<LocalDate> queryBetween(LocalDate from, LocalDate to);

  /**
   * 查询日期所属月份的第一个交易日
   * @param date 当前日期
   * @return 交易日
   */
  Optional<LocalDate> queryFirstTradingDayOfMonth(LocalDate date);
}
