import type {AuthResult, Login} from "../http/generated";
import {writable} from "svelte/store";

const userKey = 'user';
const authKey = 'authData';
const emptyLogin: Login = {username: null, password: null}

export const store = {
  setLogin: (value: Login) => localStorage.setItem(userKey, JSON.stringify(value)),
  getLogin: (): Login => JSON.parse(localStorage.getItem(userKey)) || emptyLogin,
  clearLogin: () => localStorage.removeItem(userKey),
  setAuthData: (value: AuthResult) => localStorage.setItem(authKey, JSON.stringify(value)),
  getAuthData: (): AuthResult => JSON.parse(localStorage.getItem(authKey)),
  clearAuthData: () => localStorage.removeItem(authKey),
}

export const login = writable<Login>(store.getLogin())
export const authData = writable<AuthResult>(store.getAuthData())

const unsubscribe = authData.subscribe(value => {
  store.setAuthData(value);
})

export function cleanup() {
  if (unsubscribe) {
    unsubscribe();
  }
}
