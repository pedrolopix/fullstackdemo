// store.js
import type {Login} from "$lib/http/generated";
import {httpClient} from "$lib/http/HttpClient";

export const authService = {
  doLogin: async (username: string, password: string, rememberMe: Boolean) => {
    const login: Login = {username, password};
    if (rememberMe) {

    } else {

    }
    const response = await httpClient.auth.apiAuthSigninPost(login,
        {
          headers: {
            Authorization: null
          }
        });
    if (response.status === 200) {
      console.log("login ok")

    }
  },
  logout: () => {

  }
}
