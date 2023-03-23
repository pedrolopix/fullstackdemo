// store.js
import type {Login} from "../http/generated";
import {httpClient} from "../http/HttpClient";
import {navigateTo} from "svelte-router-spa";
import {dashboardModule} from "../settings";
import {authData, store} from "./AuthStore";

export const authService = {
  doLogin: async (username: string, password: string, rememberMe: Boolean) => {
    const login: Login = {username, password};
    if (rememberMe) {
      store.setLogin(login);
    } else {
      store.clearLogin();
    }
    const response = await httpClient.auth.apiAuthSigninPost(login);
    if (response.status === 200) {
      console.log("login ok")
      authData.set(response.data);
      navigateTo(dashboardModule.href);
    }
  },
  logout: () => {
    store.clearLogin();
    navigateTo("login");
  }
}
