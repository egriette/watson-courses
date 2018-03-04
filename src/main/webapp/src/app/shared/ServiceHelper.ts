
export class ServiceHelper {

  static buildUrl(url: string, user: string): string {
    const re = /:user/gi;
    const newstr = url.replace(re, user);
    return newstr;
  }

}
