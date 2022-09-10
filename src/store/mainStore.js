import { configureStore } from "@reduxjs/toolkit";

import currentGameReducer from '../reducers/currentGameReducer';




export const store = configureStore({reducer: currentGameReducer});



export default store;

// import {loadState, saveState } from './localStorage'

// const mainStore = () => {
//     const persistedState = loadState()
//     const store = createStore (currentGameReducer, persistedState);

//     store.subscribe (()=>{
//         saveState({
//             todos:store.getState.todos
//         })
//     })

//     return store
// }

// export default mainStore;