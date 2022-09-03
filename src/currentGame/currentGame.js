import { configureStore } from "@reduxjs/toolkit";
import currentGameReducer from '../reducers/index';

const currentGame = configureStore({ reducer: currentGameReducer});

export default currentGame;