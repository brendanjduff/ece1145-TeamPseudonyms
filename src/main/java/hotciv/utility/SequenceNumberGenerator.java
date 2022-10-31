package hotciv.utility;

import java.util.List;

public class SequenceNumberGenerator implements NumberGenerator {

  List<Integer> sequence;
  int index = 0;

  public void setNumbers(List<Integer> numbers) {
    sequence = numbers;
  }

  @Override
  public int d6() {
    return (sequence.get((index++ % sequence.size())) % 6) + 1;
  }
}
