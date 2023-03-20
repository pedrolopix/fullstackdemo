import {AuthResourceApi, GreetingResourceApi} from "./generated";
import applyCaseConverter from "axios-case-converter";
import axios from "axios";
import {navigateTo} from "svelte-router-spa";

const axiosInstance = applyCaseConverter(
    axios.create({
      timeout: 10000,
      headers: {
        "Content-Type": "application/json"
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
        navigateTo("/login"); // Redirect to login page
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



// axios.interceptors.request.use(
//     async (config) => {
//       const loginResult: LoginResult = JSON.parse(localStorage.getItem("session"));
//
//       if (loginResult?.token) {
//         config.headers = {
//           ...config.headers,
//           authorization: `Bearer ${loginResult?.token}`,
//         };
//       }
//       return config;
//     },
//     function (error) {
//       console.log(error);
//       return Promise.reject(error);
//     }
// );

const getFactory = (baseUrl: string) => ({
  hello: new GreetingResourceApi(undefined, baseUrl, axiosInstance),
  auth: new AuthResourceApi(undefined, baseUrl, axiosInstance),
});

export const httpClient = getFactory("http://localhost:8080")

