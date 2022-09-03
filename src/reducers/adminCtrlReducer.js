
const adminCtrlReducer = (state =  {
    type: 'ADMIN_MENU',
    payload: "0"
  }, action) => {
  switch(action.type) {
    case 'CURRENT_GAME':
      return Object.assign({}, state, { ...state, currentGame: action.payload });
      break;
    default:
      return state;
  }
}


export default adminCtrlReducer;