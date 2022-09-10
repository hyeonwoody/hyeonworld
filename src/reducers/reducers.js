import { combineReducers } from "redux";
import currentGameReducer from "./currentGameReducer";

const rootReducer = combineReducers({
    currentGameReducer
});

export default rootReducer;