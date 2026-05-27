Phần 1 - Phân tích logic: Dựa trên mã nguồn hiện có và các quy tắc nghiệp vụ, hãy phân tích tại sao số lượng tồn kho
không chính xác. Có bao nhiêu lỗi logic trong phương thức updateStock?

Trả lời:
- thực hiện product.setStockQuantity(newStock) nhưng chưa lưu thay
đổi đó vào cơ sở dữ liệu

- Thiếu kiểm tra  đkien đồng thời: Đây là lỗi nghiêm trọng nhất trong bài tập này. Nếu hai người cùng thực hiện
updateStock cho một sản phẩm cùng lúc, cả hai sẽ đọc currentStock như nhau, tính toán newStock và ghi đè lên nhau. Điều
này dẫn đến sai lệch tồn kho thực tế.

- Xử lý Ngoại lệ : Khi productOpt.isEmpty(),  ném IllegalArgumentException, nếu không tìm thấy dữ liệu, người ta thường dùng EntityNotFoundException để hệ thống phân biệt
được đâu là lỗi do người dùng nhập sai, đâu là lỗi do hệ thống dữ liệu.