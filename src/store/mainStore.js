import { configureStore } from "@reduxjs/toolkit";

import currentGameReducer from '../reducers/currentGameReducer';



export const store = configureStore({reducer: currentGameReducer});

export default store;