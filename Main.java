
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String[] split = line.split(" ");
		int n = Integer.valueOf(split[0]);
		int l = Integer.valueOf(split[1]);
		int t = Integer.valueOf(split[2]);

		List<Ball> balls = new LinkedList<>();

		String ballInfo = sc.nextLine();
		String[] ballLocation = ballInfo.split(" ");
		for (int i = 0; i < ballLocation.length; i++) {
			balls.add(new Ball(true, Integer.valueOf(ballLocation[i])));
		}
		sc.close();

		for (int i = 0; i < t; i++) {
			for (Ball ball : balls) {
				if (ball.direction) {
					ball.position++;
				} else {
					ball.position--;
				}
				if (ball.position == 0 || ball.position == l) {
					ball.direction = (!ball.direction);
				}
			}
			List<Ball> tempBalls = new LinkedList<>();
			Iterator<Ball> iterator = balls.iterator();
			while (iterator.hasNext()) {
				Ball ball = iterator.next();
				iterator.remove();
				if (balls.contains(ball)) {
					balls.get(balls.indexOf(ball)).direction = !balls.get(balls.indexOf(ball)).direction;
					ball.direction = !ball.direction;
				}
				tempBalls.add(ball);
			}
			balls=tempBalls;

		}
		Collections.sort(balls, (Ball x, Ball y) -> {
			return x.position - y.position;
		});
		String s = "";
		for (Ball ball : balls) {
			s += ball.position + " ";
		}
		System.out.print(s.substring(0, s.length() - 1));

	}

	static class Ball {
		// 小球运动方向 true 为向右
		boolean direction;
		// 小球位置
		int position;

		public Ball(boolean fangxiang, int weizhi) {
			super();
			this.direction = fangxiang;
			this.position = weizhi;
		}

		@Override
		public boolean equals(Object obj) {
			Ball o = null;
			if (obj instanceof Ball) {
				o = (Ball) obj;
			} else {
				return false;
			}
			return o.position == this.position;
		}

	}

}
