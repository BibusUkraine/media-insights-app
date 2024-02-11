import { createSlice } from '@reduxjs/toolkit';

type AppSliceState = {
    filter: string[];
}

const initialState:AppSliceState = {
    filter: [],
}

export const appSlice = createSlice({
    name: 'app',
    initialState,
    reducers: {
        addFilter: (state, action) => {
            state.filter.push(action.payload);
        },
        removeFilter: (state, action) => {
            state.filter = state.filter.filter((item) => item !== action.payload);
        },
    },
})

export const { addFilter,removeFilter } = appSlice.actions

export default appSlice.reducer