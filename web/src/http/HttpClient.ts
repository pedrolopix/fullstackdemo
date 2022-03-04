
import React from 'react';
import HttpContext from './HttpClientContext';

const useHttpClient = () => {
  const httpClient = React.useContext(HttpContext);

  if (!httpClient) {
    throw new Error('The Http Client was not initialized.');
  }

  return httpClient;
};

export default useHttpClient;
