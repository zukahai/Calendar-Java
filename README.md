## <p align="center"> Thử làm Calendar bằng Java </p>
<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Calendar/1.png" alt="blog" /> </p>

## Thử Làm Một Calendar Xịn Xò

### Giới thiệu về Calendar

Một carlendar cơ bản có thể cho ta biết được hôm nay là ngày nào và tất cả các ngày trong một tháng nào đó rơi vào thứ mấy, như hình bên dưới:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Calendar/oo.png" alt="blog" /> </p>

Ngoài ra nó còn có thể thêm một số tính năng như tạo có sự kiện trong ngày. Thật là hấp dẫn đúng không, không dài dòng nữa, chúng ta cùng bắt tay ngày vào làm nào.

### Tạo Calendar

Trước tiên chúng ta cần biết một số thông tin cần thiết:

Năm nhuận:
Người ta quy định rằng năm chia hết cho 4 và không chia hết cho 100 và những năm chia hết cho 400 là nhưng năm nhuận, ví dụ 2020, 2024, 2000,... là những năm nhuận, 2021, 2100, 2345,... không phải là năm nhuận, những năm nhuận sẽ có 366 ngày, còn những năm khác sẽ có 365 ngày.

Hàm kiểm tra năm nhuận:

```java
	public boolean isLeapYear(int  N) {
		if (N % 4 == 0 && N % 100 != 0)
			return true;
		if (N % 400 == 0)
			return true;
		return false;
	}
```
Số ngày trong tháng:

Số ngày trong các tháng trừ tháng 2 là không thay đổi, với tháng 2 trong năm nhuận sẽ có 29 ngày, ngược lại sẽ có 28 ngày, các tháng 1, 3, 5, 7, 8, 10, 12 sẽ có 31 ngày, các tháng 4, 6, 9, 11 sẽ có 30 ngày:

Hàm xác định số ngày trong tháng:

```java
	public int Nday(int month, int year) {
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if (isLeapYear(year))
					return 29;
				return 28;
			}
		return 0;
	}
```

Một calendar sẽ xác định được các tất cả các ngày trong một tháng sẽ rơi vào thứ mấy, để xác định được điều đó ta cần lấy một ngày làm mốc, ở đây mình lấy ngày 01/01/0001 làm mốc và nó rơi và chính mình đánh xác định được đó là ngày thứ 2 (monday)

Như vậy kể từ ngày ngày 1 tháng 1 năm 1 thì:

- 7 * K ngày sau cũng là thứ 2
- 7 * K + 1 ngày sau sẽ là thứ 3
- ...
- 7 * K + 6 ngày sau sẽ là ngày chủ nhật
Ta sẽ chỉ cần xác định thứ của ngày đầu tiên (ngày 1) trong tháng đó, ta cần biết ngày đó các ngày mình chọn làm mốc bào nhiêu ngày.

Cách làm của mình như sau:

Ví dụ ta cần tính ngày 01/m/y cách ngày 01/01/0001 bao nhiều ngày ta sẽ chia thành 2 phần:

- Phần 1 là số ngày từ 01/01/0001 đến 01/01/y
- Phần 2 là số ngày từ 01/01/y đến 01/m/y
Tổng của 2 phần chính là số ngày ta cần tìm, phần 2 sẽ được tính rất đơn giản chính là tổng số ngày các tháng từ tháng 1 đến tháng m - 1, phần 1 chính là (y - 1) * 365 + số năm nhuận từ năm 1 đến năm y - 1.

Còn phần 1 chúng ta sẽ tính như thế nào, các bạn có thể dùng vào for từ năm thứ 1 đến năm thứ y - 1, kiểm tra xem năm đó có phải năm nhuận hay không, nếu năm nhuận cộng thêm 366 ngày, ngược lại công thêm 365 ngày, nhưng với một năm rất lớn thì các này chưa tối ưu lắm.

Mình đã nghĩ ra một cách tính số năm nhuận từ 1 đến N trong khi làm project này, và mình sẽ chia sẻ với các bạn:

Năm nhuận là năm chia hết cho 4 và không chia hết cho 100, hoặc là năm chia hết cho 400, hãy nhìn biểu đồ ven bên dưới:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Calendar/2.png" alt="blog" /> </p>

Vòng ngoài cũng sẽ là các số chia hết cho 4, vòng thứ 2 nằm trong vòng thứ nhất là các số chia hết cho 100 (vì tấ cả cả số chia hết cho 100 để chia hết cho 4), vòng thứ 3 nằm trong vòng thứ 2 là các số chia hết cho 4000 vì tấ cả cả số chia hết cho 400 để chia hết cho 100).

Nhắc lại năm nhuận là năm chia hết cho 4 và không chia hết cho 100, hoặc là năm chia hết cho 400, như vậy phần chứa năm nhuận sẽ là phần màu cam:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Calendar/3.png" alt="blog" /> </p>

Ký hiệu [a/b] và phần nguyên của a chia cho b, lúc này:

- Số lượng năm chia hết cho 4 từ 1 đến N là [N/4]
- Số lượng năm chia hết cho 100 từ 1 đến N là [N/100]
- Số lượng năm chia hết cho 4 từ 400 đến N là [N/400]
- Lúc này số năm nhuận từ 1 đến N sẽ là [N/4] - [N/100] + [N/400]

Như vậy số ngày ở phần 1 được tính bằng (y - 1) * 365 + [(y - 1)/4] - [(y - 1)/100] + [(y - 1)/400]

Hàm tính số ngày từ ngày 01/01/0001 đến ngày 01/m/y được viết như sau:

```java
	public static int getDay(int month, int year) {
		int N = year - 1;
		int d = N * 365 + N / 4 - N / 100 + N / 400;
		for (int i = 1; i < month; i++)
			d += Nday(i, N + 1);
		return d;
	}

	public static int getThu(int month, int year) {
		return getDay(month, year) % 7 + 2;
	}
```

Thế là đã xong sau khi tính được số ngày ta sẽ lấy phần dư của nó cho 7 là đã có thể tìm ra được thứ của ngày 01 tháng chỉ định rồi, từ thứ của ngày 1 ta sẽ tìm ra được thứ tất cả ngày trong tháng.

Nhận xét rằng nếu dùng một ma trận để lưu thứ và các ngày trong tháng sẽ cần dùng tới ma trận kích thứowcs 6x7 (gồm 7 cột và 6 hàng), 7 cột sẽ là 7 thứ trong tuần.

Hàm trả về ma trận các ngày trong tháng month năm year:

```java
	public static int[][]  update(int month, int year) {
		int a[][] = new int[6][7];
		int thu = getThu(month, year);
		int day = Nday(month, year);
		int pday = 0;
		if (month > 1)
			pday = Nday(month - 1, year);
		else
			pday = Nday(12, year - 1);
		int start = thu - 1;
		if (start == 7)
			start = 0;
		
		int I = 0, J = start;
		for (int i = 1; i <= day; i++) {
			a[I][J] = i;
			J++;
			if (J == 7) {
				J = 0;
				I++;
			}
		}
		for (int i = start - 1; i >= 0; i--) {
			a[0][i] = pday--;
		}
		int st = 1;
		while (!(I == 6 && J == 0)) {
			a[I][J] = st++;
			J++;
			if (J == 7) {
				J = 0;
				I++;
			}
		}
		return a;
	}
```

Ví dụ đầu vào month = 6, year = 2021 thì hàm sẽ trả về matrix là:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Calendar/4.png" alt="blog" /> </p>

Việc mình cần làm là đưa ma trận này vào giao diện của mình thôi.

## Kết

Thế là mình đã trình bày cách mà mình đã tạo ra một calendar khá là xịn xò, các bạn có thể làm thêm một số tính năng như tạo ghi chú cho mỗi ngày.


Video demo:

[<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Calendar/5.png" alt="blog" /> </p>](https://www.youtube.com/watch?v=P1EzGFPhGh8&ab_channel=HaiZuka)











## <p align="center">  :tv: Thanks for whatching :earth_africa: </p>
