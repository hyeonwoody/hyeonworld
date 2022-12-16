
const initialState ={
  STAGE : -1,
  CURRENT_GAME: -1
}

const currentGameReducer = (state =  initialState, action) => {
    switch(action.type) {
      case 'CURRENT_GAME':
        return Object.assign({}, state, { ...state, CURRENT_GAME: action.payload });
        //return Object.assign({CURRENT_GAME: action.payload });
      case 'STAGE':
        return Object.assign({},state, { ...state, STAGE: action.payload })
      default:
        return state;
    }
  }

  

  export default currentGameReducer;