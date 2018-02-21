const SIGNUP_MSG = {
    EMAIL: {
        EMPTY: "이메일을 입력해주세요.",
        LENGTH: "이메일은 5자 이상, 30자 이하이어야 합니다.",
        AT: "이메일은 @를 포함해야 합니다.",
        DOT_LOCATION: "'.'에서 '.'의 위치가 잘못되었습니다."
    },

    PASSWORD: {
        EMPTY: "비밀번호를 입력해주세요",
        LENGTH: "비밀번호는 10자 이상, 30자 이하이어야 합니다.",
        PATTERN: "비밀번호는 문자/숫자를 각각 1개 이상, 특수문자를 2개 이상 포함해야 합니다."
    },

    NAME: {
        EMPTY: "사용자 이름을 입력해주세요."
    }
}

export default SIGNUP_MSG;