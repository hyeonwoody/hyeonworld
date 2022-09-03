
const { configureStore, combineReducers } = require('@reduxjs/toolkit');
// data 기본
const nameData = { name: 'Hanamon' }
// action 객체
const actionChangeName = (newname) => {
  return {
    type: 'ACTION_CHANGE_NAME',
    payload: newname
  }
}

// reducer 함수
const postReducer = (state = nameData, action) => {
  switch(action.type) {
    case 'ACTION_CHANGE_NAME':
      return Object.assign({}, state, { ...state, name: action.payload });
      break;
    default:
      return state;
  }
}
// store 생성 및 리듀서 등록
const store = configureStore({reducer: postReducer});
// 수정 전 확인
console.log(store.getState());
// 수정 실행 (이 부분에서 예를들어 클릭 시 실행되게 끔 만들면 된다.)
store.dispatch(actionChangeName('하나a몬'));
// 수정 후 확인
console.log(store.getState());

const changeCurrentGame = (game_id) => {
    return {
      type: 'CURRENT_GAME',
      payload: game_id
    }
  }

const currentGameReducer = (state = -1, action) => {
    switch(action.type) {
      case 'CURRENT_GAME':
        return Object.assign({}, state, { ...state, currentGame: action.payload });
        break;
      default:
        return state;
    }
  }
  // store 생성 및 리듀서 등록
  const currentGame = configureStore({reducer: currentGameReducer});
  // 수정 전 확인
  console.log(currentGame.getState());
  // 수정 실행 (이 부분에서 예를들어 클릭 시 실행되게 끔 만들면 된다.)
  currentGame.dispatch({
    type: 'CURRENT_GAME',
    payload: -1
  });
  // 수정 후 확인
  console.log(currentGame.getState());

