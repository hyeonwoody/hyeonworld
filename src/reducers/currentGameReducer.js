import { createReducer } from "@reduxjs/toolkit";

const currentGameReducer = (state =  {
      CURRENT_GAME: -1,
      STAGE: -1,
    }, action) => {
    switch(action.type) {
      case 'CURRENT_GAME':
        return Object.assign({}, state, { ...state, CURRENT_GAME: action.payload });
        //return Object.assign({CURRENT_GAME: action.payload });
        break;
      case 'STAGE':
        return Object.assign({},state, { ...state, STAGE: action.payload })
      default:
        return state;
    }
  }

  

  export default currentGameReducer;