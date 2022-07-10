# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션


## 도메인

### Station

- 상태
  - 역은 식별자와 이름을 가진다.
  - 이름은 2글자 이상이어야 한다.

### Line

- 상태
  - 노선은 식별자, 이름, 색, 거리, 역들의 정보를 가진다.
  - 이름은 2글자 이상이어야 한다.
  - 색은 4글자 이상이어야 한다.
  - 거리는 1 이상이어야 한다.
  - 역들 안에 들어가는 역은 최소 두 개이다.
  - 역들 안에는 상행 종점역과 하행 종점역이 포함된다.

### LineService

- 행위
  - 노선을 생성한다.
    - 이름과 색, 상행 종점역의 식별자와 하행 종점역의 식별자, 거리를 입력받아 새로운 노선을 생성한다.
    - 상행 종점역과 하행 종점역을 식별자로 조회할 수 있어야 한다.
  - 노선을 수정한다.
    - 식별자와 이름, 색을 입력받아 기존 Line을 수정한다.
  - 노선을 삭제한다.
    - 식별자를 입력받아 노선을 삭제한다.
  - 노선을 조회한다.
      - 식별자를 입력받아 노선을 조회할 수 있다.
      - 식별자, 이름, 색, 거리, 역들의 정보를 조회한다.
  - 노선 목록을 조회한다.
