const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    ["/login", "/signup", "/user", "/account", "/profile", "product"],
    createProxyMiddleware({
      target: "http://54.206.147.12", 
      changeOrigin: true, 
      router : {

      }
    })
  );
};