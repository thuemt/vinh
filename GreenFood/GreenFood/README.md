# 🌿 Green Food – Cứu thực phẩm dư, tiết kiệm tiền, bảo vệ môi trường

> Đồ án môn Lập trình di động – Trường ĐH Giao thông Vận tải TP.HCM (UTH) – Tuần 1

## Thành viên nhóm
| STT | Họ tên | MSSV | Vai trò |
|---|---|---|---|
| 1 | _Điền tên_ | _MSSV_ | Nhóm trưởng / UI-UX |
| 2 | _Điền tên_ | _MSSV_ | Developer |
| 3 | _Điền tên_ | _MSSV_ | Phân tích / Tài liệu |

## Vấn đề
Quán ăn, tiệm bánh, siêu thị bỏ đi nhiều thực phẩm còn dùng được vào cuối ngày. Trong khi đó sinh viên và người đi làm cần bữa ăn ngon, giá rẻ.

## Giải pháp
Green Food kết nối cửa hàng với người dùng để bán **túi thực phẩm bất ngờ (surprise bag)** giá rẻ, trong khung giờ nhận hàng cuối ngày. Người dùng đặt trước, thanh toán trong app, nhận hàng bằng mã QR.

## Gap thị trường
Chưa có nền tảng địa phương hóa cho Việt Nam kết hợp: (1) đồ dư giá rẻ, (2) đối tác là quán nhỏ/tiệm bánh, (3) đo lường tác động xanh. Chi tiết: [docs/competitor-analysis.md](docs/competitor-analysis.md).

## Tính năng chính
Tìm túi gần bạn · Chi tiết cửa hàng · Giỏ hàng/thanh toán · Mã QR nhận hàng · Thống kê tác động xanh · Hồ sơ cá nhân · Cổng dành cho cửa hàng.

## Thiết kế
Chạy prototype tương tác: mở `prototype/index.html` (hoặc dùng extension **Live Server** trong VS Code → Right click → *Open with Live Server*).

## Giao diện
| | | | |
|---|---|---|---|
| ![](docs/images/02-trang-chu.png) | ![](docs/images/03-chi-tiet-tui.png) | ![](docs/images/05-ma-qr.png) | ![](docs/images/08-ho-so.png) |

## Tài liệu
- **[BÁO CÁO ĐỒ ÁN HOÀN CHỈNH](BAO_CAO.md)**
- [Phân tích đối thủ](docs/competitor-analysis.md)
- [Đề xuất dự án & mô hình doanh thu](docs/proposal.md)
- [Bài HAA & AI](docs/haa-report.md)
- [Bài 30 phút](docs/reflection.md)

## Đẩy lên GitHub
```bash
git init
git add .
git commit -m "Tuan 1: Green Food"
git branch -M main
git remote add origin https://github.com/<ten-ban>/GreenFood.git
git push -u origin main
```
