import { combineReducers } from 'redux';
import kakaoAuth from './kakaoAuth/reducer';

const rootReducer = combineReducers({
  kakaoAuth: kakaoAuth,
});

export default rootReducer;
