import type {AuthResult} from "$lib/http/generated";
import {AuthResourceApiFactory, GreetingResourceApiFactory} from "$lib/http/generated";
import axios from "axios";

const axiosInstance =
    axios.create({
      timeout: 10000,
      headers: {
        "Content-Type": "application/json"
      }
    }
);

const getFactory = (baseUrl: string) => {
  console.log("base url: " + baseUrl);
  return {
    hello: GreetingResourceApiFactory(undefined, baseUrl, axiosInstance),
    auth: AuthResourceApiFactory(undefined, baseUrl, axiosInstance),
  }
};

export const httpClient = getFactory(import.meta.env.BASE_URL)

