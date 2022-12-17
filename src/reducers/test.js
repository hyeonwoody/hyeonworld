//get redux module
const { configureStore } = require('@reduxjs/toolkit');

//data basic
const nameData = {name : 'Hanamon'}

// reducer fuction
const postReducer = (state = nameData, action)=>{
    switch(action.type){
        case 'ACTION_CHANGE_NAME':
            return Object.assign({}, state, {...state, name:action.payload})
    }
}

const currentGameReducer = (state =  {
      STAGE: '-1',
    }, action) => {
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


const store = configureStore(currentGameReducer)
console.log(store.getState())
store.dispatch(actionChangeName('하나몬'));
console.log(store.getState())