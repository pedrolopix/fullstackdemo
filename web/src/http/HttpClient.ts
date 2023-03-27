import type {AuthResult} from "./generated";
import {AuthResourceApiFactory, GreetingResourceApiFactory} from "./generated";
import applyCaseConverter from "axios-case-converter";
import axios from "axios";
import {authData, store} from "../auth/AuthStore";
import {loginModule} from "../settings";
import {navigateTo} from "svelte-router-spa";

let auth: AuthResult = null;
let refresh = false;

const axiosInstance = applyCaseConverter(
    axios.create({
      timeout: 10000,
      headers: {
        "Content-Type": "application/json"
      }
    })
);

function setAuthData(value: AuthResult) {
  console.log("token changed");
  auth = value;
  axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${auth?.token}`;
}

authData.subscribe(value => {
  setAuthData(value);
});

const gotoLogin = () => {
  console.log("redirect to login");
  navigateTo(loginModule.href);
}

axiosInstance.interceptors.response.use(resp => resp, async error => {
  if (error.response.status === 401 && !refresh) {
    refresh = true;
    const response = await refreshToken();
    if (response.status === 200) {
      store.setAuthData(response.data);
      setAuthData(response.data);
      error.config.headers.Authorization = `Bearer ${response.data.token}`;
      return axiosInstance(error.config);
    }
  }
  refresh = false;
  gotoLogin();
  return
});

const refreshToken = async () => {
      try {
        console.log("refreshToken")
        const response = await httpClient.auth.apiAuthRefreshGet(
            {
              headers: {
                Authorization: `Bearer ${auth?.refreshToken}`
              }
            }
        )
        const authResult = response.data;
        if (!authResult?.token) {
          console.log("refreshToken failed")
          store.clearAuthData();
          return response
        }
        console.log("refreshToken ok")
        return response;
      } catch (error) {
        console.log("refreshToken failed", error)
        store.clearAuthData();
      }
    }
;

// axiosInstance.interceptors.request.use(async (config) => {
//       if (config.url.endsWith("/auth/signin")) {
//         return config;
//       }
//       let token = auth?.token;
//       if (config.url.endsWith("/auth/refresh")) {
//         token = auth?.refreshToken;
//       }
//       if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//       } else {
//         gotoLogin();
//       }
//       return config;
//     },
//     function (error) {
//       console.log(error);
//       return Promise.reject(error);
//     }
// );

const getFactory = (baseUrl: string) => ({
  hello: GreetingResourceApiFactory(undefined, baseUrl, axiosInstance),
  auth: AuthResourceApiFactory(undefined, baseUrl, axiosInstance)
});

export const httpClient = getFactory("http://localhost:8080")

