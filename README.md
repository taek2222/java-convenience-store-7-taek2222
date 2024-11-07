# 편의점 🏪

## 기능 구현 목록

### ✅ 물품 등록
- [x] 이름과 가격으로 물품을 등록한다
- [ ] 이름으로 물품 가격을 반환한다
- [x] `예외처리` 등록 이름이 공백일 경우 예외가 발생한다
- [ ] `예외처리` 등록 가격이 1원보다 작을 경우 예외가 발생한다

### ✅ 계산 기능
- [ ] 상품별 가격과 수량 곱으로 총구매액 계산
- [ ] 프로모션 할인 정책 계산
- [ ] 멤버십 할인 정책 계산

---
### ✅ 재고 관리 기능
- [ ] 재고 수량 확인 후, 결제 가능 여부
- [ ] 상품 구매 시 재고 차감
- `예외처리` 구매할 상품과 수량 형식이 올바르지 않은 경우 `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`
- `예외처리` 존재하지 않는 상품을 입력한 경우 `[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`
- `예외처리` 구매 수량이 재고 수량을 초과한 경우 `[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`

---
### ✅ 프로모션 할인 기능
- [ ] 프로모션 기간 내에 포함된 경우만 할인 적용
- [ ] 프로모션은 지정된 상품에만 적용
- [ ] 동일 상품에 여러 프로모션 적용 X
- [ ] 혜택은 프로모션 상품 재고만 적용
- [ ] 프로모션 재고 부족 시, 동일 상품의 일반 재고 사용
- [ ] 프로모션 수량보다 적게 가져올 때, 추가 구매 시 혜택 안내
- [ ] 프로모션 수량보다 많게 가져올 때, 정가 결제 주의 안내

---
### ✅ 멤버십 할인 기능
- [ ] 멤버십 회원은 프로모션 미적용 상품의 `30%` 할인
- [ ] 프로모션 적용 후, 남은 금액에 대한 멤버십 할인
- [ ] 할인 최대 한도 `8,000`원
- [ ] `예외처리` 할인 적용 후 `내실돈` 금액이 음수면 0을 반환한다.

---
### ✅ 영수증 출력 기능
- [ ] 구매 내역과 할인을 출력
    - [ ] 구매 내역 : 상품명, 수량, 가격
    - [ ] 증정 상품 내역 : 프로모션 증정 상품
    - [ ] 금액 정보
        - [ ] 총구매액 : 상품의 총 수량과 금액
        - [ ] 행사할인 : 프로모션으로 할인된 금액
        - [ ] 멤버십할인 : 멤버십으로 할인된 금액
        - [ ] 내실돈 : 최종 결제 금액
- [ ] 영수증 구성 요소 정렬
```
ex)
==============W 편의점================
상품명		수량	금액
콜라		10 	10,000
=============증	정===============
콜라		2
====================================
총구매액		10	10,000
행사할인			-2,000
멤버십할인			-0
내실돈			 8,000
```

---
### ✅ 그 외 기능
- [ ] 영수증 출력 후 재시작 여부 확인

---
### ✅ 에러 처리
- [ ] 잘못된 값 입력 시 `[ERROR] ` 에러 메시지 출력 후, 다시 입력
- [ ] `Exception`이 아닌, `IllegalArgumentException`와 `IllegalStateException` 명확한 유형으로 처리

---
### ✅ 입력
- [ ] `products.md`과 `promotions.md` 파일을 입력으로 읽기
- [ ] 구매 상품과 수량
    - 수량 `(-)` 하이픈 구분
    - 상품은 `([])` 대괄호로 묶인 `(,)` 구분
```
ex) 
구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-10],[사이다-3]
```

- [ ] 프로모션 적용 가능한 추가 수량 여부
```
ex)
현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y
```
- [ ] 프로모션 적용 불가능한 수량의 정가 결제 여부
```
현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y
```
- [ ] 멤버십 할인 적용 여부
```
멤버십 할인을 받으시겠습니까? (Y/N)
Y
```
- [ ] 추가 구매 여부
```
감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
N
```
- `예외처리` 잘못된 입력의 경우 `[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`

---
### ✅ 출력
- [ ] 환영 인사 출력 `ex) 안녕하세요. W편의점입니다.`
- [ ] 보유 물품 안내 출력 `ex) 현재 보유하고 있는 상품입니다.`
- [ ] 보유 물품 출력
    - 재고 0개 : `재고 없음` 
```
ex)
- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개
```
- [ ] 구매, 증정 상품 내역 - 금액 정보 출력
```
==============W 편의점================
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
=============증	정===============
콜라		1
====================================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000
```

---
### ✅ 실행 결과
```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[에너지바-5]

멤버십 할인을 받으시겠습니까? (Y/N)
Y

==============W 편의점================
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
=============증	정===============
콜라		1
====================================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 7개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-10]

현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
N

==============W 편의점================
상품명		수량	금액
콜라		10 	10,000
=============증	정===============
콜라		2
====================================
총구매액		10	10,000
행사할인			-2,000
멤버십할인			-0
내실돈			 8,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 재고 없음 탄산2+1
- 콜라 1,000원 7개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[오렌지주스-1]

현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
Y

==============W 편의점================
상품명		수량	금액
오렌지주스		2 	3,600
=============증	정===============
오렌지주스		1
====================================
총구매액		2	3,600
행사할인			-1,800
멤버십할인			-0
내실돈			 1,800

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
N
```