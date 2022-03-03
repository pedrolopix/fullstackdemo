import axios from 'axios';
import applyCaseConverter from 'axios-case-converter';

const axiosInstance = applyCaseConverter(
    axios.create({
      timeout: 10000,
    })
);

export default axiosInstance;
