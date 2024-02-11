import axios from "axios";

export const getAllNews = async (filter: string,page:number) => {

 try {
  const res = await axios.get(`http://mediainsightsservice.northeurope.cloudapp.azure.com:8080/api/articles?query=${filter}&page=${page}`);
  return res.data;
 } catch (error) {
  console.error("Error fetching news:", error);
  throw error;
 }
};