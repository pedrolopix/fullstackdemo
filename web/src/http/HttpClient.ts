import type {AuthResult} from "./generated";
import {AuthResourceApiFactory, GreetingResourceApi} from "./generated";
import applyCaseConverter from "axios-case-converter";
import axios from "axios";
import {navigateTo} from "svelte-router-spa";
import {loginModule} from "../settings";
import {authData} from "../auth/AuthStore";

let auth: AuthResult = null;
authData.subscribe(value => {
  console.log("token changed");
  auth = value;
});

const axiosInstance = applyCaseConverter(
    axios.create({
      timeout: 10000,
      headers: {
        "Content-Type": "application/json",
        "Authorization": "bearer ",
      }
    })
);
axiosInstance.interceptors.response.use(
    (response) => {
      return response;
    },
    (error) => {
   
      console.log("axios error", error)
      if (error.response.status === 401) {
        console.log("redirect to login")
        navigateTo(loginModule.href); // Redirect to login page
      }
      return Promise.reject(error);
    }
);

// const refreshTokenFn = async () => {
//   try {
//     const response = await httpClient.login.apiRefreshGet();
//     const loginResult = response.data;
//     if (!loginResult?.token) {
//       localStorage.removeItem("session");
//     }
//     localStorage.setItem("session", JSON.stringify(loginResult));
//     return loginResult;
//   } catch (error) {
//     localStorage.removeItem("session");
//   }
// };

axiosInstance.interceptors.request.use(
    async (config) => {
      if (config.url.endsWith("/auth/signin")) {
        return config;
      }
      const token = auth?.token;
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    },
    function (error) {
      console.log(error);
      return Promise.reject(error);
    }
);
const getFactory = (baseUrl: string) => ({
  hello: new GreetingResourceApi(undefined, baseUrl, axiosInstance),
  auth: AuthResourceApiFactory(undefined, baseUrl, axiosInstance) // new AuthResourceApi(undefined, baseUrl, axiosInstance),
});

export const httpClient = getFactory("http://localhost:8080")

