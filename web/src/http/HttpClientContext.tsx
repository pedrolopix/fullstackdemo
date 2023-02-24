import React, {useEffect, useState} from "react";
import {GreetingResourceApi} from "./generated";
import {AxiosInstance} from "axios";
import axiosInstance from './getAxiosInstance';

// @ts-ignore
const HttpContext = React.createContext<HttpClientFactoryContext>(undefined);

export default HttpContext;

const getFactory = (baseUrl: string) => ({
  hello: new GreetingResourceApi(undefined, baseUrl, axiosInstance),
});

type HttpClientFactoryContext = ReturnType<typeof getFactory> & {
  axiosInstance: AxiosInstance;
};

export const HttpClientProvider: React.FC<{ baseUrl: string, children?: React.ReactNode }> = ({
                                                                                                baseUrl,
                                                                                                children,
                                                                                              }) => {
  const [currentBaseUrl, setBaseUrl] = useState<string>(baseUrl);
  const [config, setConfig] = useState<HttpClientFactoryContext>(() => ({
    ...getFactory(baseUrl),
    axiosInstance,
  }));

  useEffect(() => {
    if (baseUrl === currentBaseUrl) {
      return;
    }

    setBaseUrl(baseUrl);
    setConfig({
      ...getFactory(baseUrl),
      axiosInstance,
    });
  }, [baseUrl, currentBaseUrl]);

  return <HttpContext.Provider value={config}>{children}</HttpContext.Provider>;
};
