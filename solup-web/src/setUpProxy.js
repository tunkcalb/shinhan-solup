const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    ["/login", "/signup", "/user", "/account", "/profile", "/product"],
    createProxyMiddleware({
      target: "http://54.206.147.12", 
      changeOrigin: true, 
      router : {

      }
    })
  );

  app.use(
    ["/policygrow.do", "/policygrnty.do"],
    createProxyMiddleware({
      target: " https://www.sbiz.or.kr/sup/policy/json", // 두 번째 백엔드 서버
      changeOrigin: true,
      router : {

      }
    })
  );
};