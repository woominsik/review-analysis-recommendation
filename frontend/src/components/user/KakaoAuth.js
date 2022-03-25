import React from 'react';
import styled from 'styled-components';

const Button = styled.a`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 18px;
  marign-top: 58px;
  width: 371px;
  height: 71px;
  line-height: 71px;
  font-size: 22px;
  text-align: center;
  color: #f0ffff;
  cursor: pointer;
  background: linear-gradient(90deg, #0546d6, #3f89fc);
  text-decoration: none;
  box-shadow: 0 15px 14px rgb(0 42 177 / 12 %);
`;

function KakaoAuth() {
//  const CLIENT_ID = 'eee625cbb045b79d20d99ba8583a55f4';
  const CLIENT_ID = 'cdb30d76444af81599a02d98f7e066d0';
  const REDIRECT_URI = 'http://localhost:3000/kakaoAuth';

  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  return (
    <div>
      <Button id="kakaoLoginButton" href={KAKAO_AUTH_URL}>
        카카오로 로그인하기
      </Button>
    </div>
  );
}

export default KakaoAuth;
