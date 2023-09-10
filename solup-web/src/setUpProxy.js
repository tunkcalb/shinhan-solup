const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    ["/login", "/signup"],
    createProxyMiddleware({
      target: "http://54.206.147.12", 
      changeOrigin: true, 
      router : {

      }
    })
  );
};