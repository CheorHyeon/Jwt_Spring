# Jwt_Spring

이 레포지토리는 JWT(Jason Web Tokens) 수업 내용을 정리하고 토큰 발행에 대한 실습 내용을 담고 있습니다. 아래에서는 수업에서 다룬 주요 개념과 내용을 설명합니다.

## JWT 이전 방식과의 비교
과거에는 아이폰, 안드로이드 기기에서 로그인 상태를 유지하기 위해 쿠키나 기기 내부에 ID와 PW를 저장하여 매 요청마다 이 정보를 서버에 전송하는 방식이 주로 사용되었습니다. 그러나 이 방식에는 몇 가지 문제점이 있었습니다.

- 개인정보 보호 이슈: 기기 내부에 ID와 PW를 저장하는 것은 보안적인 우려가 있었습니다.
- PW 변경의 번거로움: PW가 노출되면 PW를 변경해야 했습니다.

이런 이슈로 토큰 방식이 등장하였습니다. 토큰 방식의 특징은 아래와 같습니다.
- 개인정보 보호: 아이폰, 안드로이드 기기 내부에 민감한 정보를 저장하지 않고도 토큰을 사용하여 인증 및 권한 부여가 가능합니다.
- PW 해킹 방지: 토큰을 탈취당하더라도 새로운 토큰 발급으로 보안이 강화됩니다.
- 요청 처리 방식의 변경 : (기존) 매 요청마다 ID/PW 전송 -> (변경) 매 요청마다 토큰을 전송
  - 서버 측에서는 DB에 접속하여 사용자 (ID/PW -> 토큰) 검증
  - 매 요청마다 사용자 검증을 위한 DB 접속 불가피(캐시Data 활용 제외)

이런 방식이 지속되다 보니 토큰에 의미 있는 데이터를 담을 수 있는 JWT가 등장하였습니다.

## JWT (JSON Web Tokens)
JWT는 JSON 형식으로 정보를 저장하고 전송하기 위한 토큰입니다. 주요 특징은 다음과 같습니다:

- 유효성 검사: 토큰의 유효성을 확인할 때 데이터베이스 조회가 필요하지 않아 속도가 빠릅니다.
- 데이터 저장: 토큰에는 필요한 데이터를 포함할 수 있습니다.
- 공개 정보: 누구나 JWT를 복호화하여 내용을 확인할 수 있습니다.
- 변조 방지: 토큰은 Base64 인코딩과 시크릿 키를 사용하여 변조가 방지됩니다.
- 토큰 관리: 토큰이 탈취되면 기존 토큰을 만료시킬 수 없으며, 유효 기간을 관리할 수 있습니다.

## JWT 발행 과정
1. 시크릿 키 원문을 사용하여 Secret Key 객체 생성.
2. JWT에 포함될 정보(Claim)를 구성.
3. 토큰 발행.